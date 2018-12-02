<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Participation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ParticipationController extends Controller
{
    public function createAction(Request $request)
    {
        $p = new Participation();
        $em = $this->getDoctrine()->getManager();
        $p->setSession($em->getRepository("AppBundle:Session")->find($request->get('session')));
        $p->getSession()->setCurrentNb($p->getSession()->getCurrentNb()+1);
        $em->persist($p->getSession());
        $em->flush();
        $p->setUser($em->getRepository("AppBundle:User")->find($request->get('user')));
        $em->persist($p);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($p);
        return new JsonResponse($formated);
    }
    public function removeAction(Request $request)
    {
        $p = new Participation();
        $em = $this->getDoctrine()->getManager();
        $p->setSession($em->getRepository("AppBundle:Session")->find($request->get('session')));
        $p->setUser($em->getRepository("AppBundle:User")->find($request->get('user')));
        $em->remove($p);
        $em->flush();

        $p->setSession($em->getRepository("AppBundle:Session")->find($request->get('session')));
        $p->getSession()->setCurrentNb($p->getSession()->getCurrentNb()-1);
        $em->persist($p->getSession());
        $em->flush();

        $ps=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Participation')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($ps);
        return new JsonResponse($formated);
    }

}
