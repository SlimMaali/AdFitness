<?php

namespace AppBundle\Controller;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class ProfileController extends Controller
{
    public function readAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user= $em->getRepository('AppBundle:User')->find($request->get('id'));
        $profile=$em->getRepository('AppBundle:Profile')->findOneBy(array('user'=>$user));
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($profile);
        return new JsonResponse($formated);
    }
    public function readAllAction(Request $request)
    {
        $profiles=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:Profile')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($profiles);
        return new JsonResponse($formated);
    }
}
