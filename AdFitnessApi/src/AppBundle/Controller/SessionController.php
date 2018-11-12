<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Room;
use AppBundle\Entity\Session;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class SessionController extends Controller
{
    public function createAction(Request $request)
    {
        $session = new Session();
        $em = $this->getDoctrine()->getManager();
        $session->setName($request->get('name'));
        $session->setDate((new \DateTime($request->get('date'))));
        $session->setLocation($em->getRepository("AppBundle:Room")->find($request->get('location')));
        $session->setDuration($request->get('duration'));
        $session->setCurrentNb($request->get('currentNb'));
        $session->setMaxNb($request->get('maxNb'));
        $em->persist($session);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($session);
        return new JsonResponse($formated);
    }
    public function updateAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $session= $em->getRepository('AppBundle:Session')->find($request->get('id'));
        $session->setName($request->get('name'));
        $session->setDate((new \DateTime($request->get('date'))));
        $session->setLocation($em->getRepository("AppBundle:Room")->find($request->get('location')));
        $session->setDuration($request->get('duration'));
        $session->setCurrentNb($request->get('currentNb'));
        $session->setMaxNb($request->get('maxNb'));
        $em->persist($session);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($session);
        return new JsonResponse($formated);
    }
    public function readAction(Request $request)
    {
        $session=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Session')->find($request->get('id'));
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($session);
        return new JsonResponse($formated);
    }
    public function readAllAction(Request $request)
    {
        $session=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Session')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($session);
        return new JsonResponse($formated);
    }
    public function deleteAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $session=$em->getRepository('AppBundle:Session')->find($request->get('id'));
        $em->remove($session);
        $em->flush();
    }

}
